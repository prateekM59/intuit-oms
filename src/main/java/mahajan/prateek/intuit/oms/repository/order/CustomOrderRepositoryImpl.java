package mahajan.prateek.intuit.oms.repository.order;

import mahajan.prateek.intuit.oms.api.model.order.SearchOrderRequest;
import mahajan.prateek.intuit.oms.repository.model.Order;
import org.junit.platform.commons.util.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: pramahajan on 11/14/20 5:36 AM GMT+05:30
 */
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private final EntityManager entityManager;

    @Inject
    public CustomOrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> searchOrders(SearchOrderRequest searchOrderRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> root = cq.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchOrderRequest.getUserId() != null) {
            predicates.add(cb.equal(root.get("userId"), searchOrderRequest.getUserId()));
        }
        if (StringUtils.isNotBlank(searchOrderRequest.getEmail())) {
            predicates.add(cb.equal(root.get("email"), searchOrderRequest.getEmail()));
        }
        if (StringUtils.isNotBlank(searchOrderRequest.getPhone())) {
            predicates.add(cb.equal(root.get("phone"), searchOrderRequest.getPhone()));
        }
        if (StringUtils.isNotBlank(searchOrderRequest.getProduct())) {
            predicates.add(cb.like(root.get("product"), "%" + searchOrderRequest.getProduct().toUpperCase().trim() + "%"));
        }

        ZonedDateTime utcTime15MinsAgo = ZonedDateTime.now(ZoneId.of("UTC")).minusMinutes(15);
        Timestamp timestamp15MinsAgo = Timestamp.from(utcTime15MinsAgo.toInstant());

        if (searchOrderRequest.getDateSearchType() == SearchOrderRequest.DateSearchType.LAST_15_MINS) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"), timestamp15MinsAgo));
        } else {
            predicates.add(cb.lessThan(root.get("date"), timestamp15MinsAgo));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
