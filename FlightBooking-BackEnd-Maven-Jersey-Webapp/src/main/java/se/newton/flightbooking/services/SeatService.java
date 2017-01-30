package se.newton.flightbooking.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import se.newton.flightbooking.models.*;

public class SeatService extends AbstractService<Seat, Long> {

    public Seat save(Seat seat, Integer parentId) {
        Session session = getSession();
        Flight flight = (Flight) session.get(Seat.class, parentId);

        if (session.contains(flight)) {
            seat.setFlight(flight);
            flight.getSeats().add(seat);

            super.save(seat);
        }

        return seat;
    }

    public List<ResultSet> SearchWithCriteriaAPI(SearchParam params) throws ParseException {

        Session session = getSession();
        Criteria criteria = session.createCriteria(Seat.class, "S");

        // Seat S INNER JOIN Flight F INNER JOIN AirPlane A INNER JOIN Company C with Alias S, F, A and C
        criteria = criteria.createAlias("S.flight", "F")
                .createAlias("F.airPlane", "A")
                .createAlias("A.company", "C");

        // select columns with projection
        criteria = criteria.setProjection(
                Projections.projectionList()
                    .add(Projections.property("F.fId"), "fId")
                    .add(Projections.property("F.fName"), "flightName")
                    .add(Projections.property("F.fromDest"), "fromDest")
                    .add(Projections.property("F.toDest"), "toDest")
                    .add(Projections.property("F.departure"), "departure")
                    .add(Projections.property("F.arrival"), "arrival")
                    .add(Projections.property("S.price"), "price")
                    .add(Projections.count("S.available"), "seatCount")
                    .add(Projections.groupProperty("C.cName"), "companyName")
        );

        Calendar calMorning = Calendar.getInstance();
        Calendar calMidnight = Calendar.getInstance();

        calMorning.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(params.date));
        calMidnight.set(calMorning.get(Calendar.YEAR), calMorning.get(Calendar.MONTH), calMorning.get(Calendar.DAY_OF_MONTH), 23, 59, 59);

        // set WHERE clause filtering
        criteria = criteria.add(Restrictions.eq("F.fromDest", params.fromDest).ignoreCase())
                .add(Restrictions.eq("F.toDest", params.toDest).ignoreCase())
                .add(Restrictions.between("F.departure", calMorning, calMidnight))
                .add(Restrictions.eq("S.sClass", params.sClass).ignoreCase())
             // .add(Restrictions.sqlRestriction("count({alias}.available) >= ?", params.adult + params.child, IntegerType.INSTANCE)) not working ...
                .add(Restrictions.eq("S.available", true));

        // set ORDERBY sorting
        String alias = params.orderBy.equalsIgnoreCase("price") ? "S." : "C.";
        criteria = criteria.addOrder(Order.asc(alias + params.orderBy));

        // transform the creteria result from List<Object[]> to List<ResultSet> entity
        List<ResultSet> result = criteria.setResultTransformer(Transformers.aliasToBean(ResultSet.class)).list();

        return result;
    }

    public List<ResultSet> SearchWithQueryAPI(SearchParam params) throws ParseException {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT F.fId, C.cName, F.fName, F.fromDest, F.toDest, F.departure, F.arrival, S.available, S.price, count(F.fName) AS seatCount FROM Seat S");
        sb.append(" INNER JOIN S.flight F INNER JOIN F.airPlane A INNER JOIN A.company C GROUP BY C.cName");
        sb.append(" HAVING lower(F.fromDest)=lower(:from) AND lower(F.toDest)=lower(:to) AND S.available=:avail AND seatCount>=:passenger AND F.departure BETWEEN :start AND :end");
        sb.append(" ORDER BY :orderBy");

        Session session = getSession();
        Query query = session.createQuery(sb.toString());

        Calendar calMorning = Calendar.getInstance();
        Calendar calMidnight = Calendar.getInstance();

        calMorning.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(params.date));
        calMidnight.set(calMorning.get(Calendar.YEAR), calMorning.get(Calendar.MONTH), calMorning.get(Calendar.DAY_OF_MONTH), 23, 59, 59);

        query.setParameter("from", params.fromDest);
        query.setParameter("to", params.toDest);
        query.setParameter("start", calMorning);
        query.setParameter("end", calMidnight);
        query.setParameter("avail", true);
     // query.setParameter("passenger", params.adult + params.child); not working ...
        query.setParameter("orderBy", params.orderBy.equalsIgnoreCase("price") ? "S.price" : "C.cName");

        // List<Object[]> result = query.list();
        // transform the query result from List<Object[]> to List<ResultSet> entity
        List<ResultSet> result = query.setResultTransformer(Transformers.aliasToBean(ResultSet.class)).list();

        return result;
    }

    public List<ResultSet> Search(SearchParam params) throws ParseException {
        return SearchWithCriteriaAPI(params);
    }
}
