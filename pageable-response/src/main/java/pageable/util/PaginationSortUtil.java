package pageable.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;

public class PaginationSortUtil {

    // 基于Pageable Sort信息来创建query排序规则
    // - 避免使用空排序order by (select 0)
    // - 支持多个排序字段
    public static String addSortToNativeQuery(String query, Pageable pageable) {
        StringBuilder strBuilder = new StringBuilder(query);
        if (!pageable.getSort().isEmpty()) {
            Iterator<Sort.Order> orderIterator = pageable.getSort().iterator();
            strBuilder.append(" ORDER BY ");
            while (orderIterator.hasNext()) {
                Sort.Order order = orderIterator.next();
                strBuilder.append(order.getProperty()).append(" ").append(order.getDirection().name());
                if (orderIterator.hasNext()) {
                    strBuilder.append(" , ");
                }
            }
        }
        return strBuilder.toString();
    }
}