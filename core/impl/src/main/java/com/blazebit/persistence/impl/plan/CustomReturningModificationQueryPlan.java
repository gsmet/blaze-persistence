package com.blazebit.persistence.impl.plan;

import com.blazebit.persistence.ReturningObjectBuilder;
import com.blazebit.persistence.ReturningResult;
import com.blazebit.persistence.impl.DefaultReturningResult;
import com.blazebit.persistence.spi.DbmsDialect;
import com.blazebit.persistence.spi.ExtendedQuerySupport;
import com.blazebit.persistence.spi.ServiceProvider;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Christian Beikov
 * @since 1.2.0
 */
public class CustomReturningModificationQueryPlan<T> implements ModificationQueryPlan, SelectQueryPlan<ReturningResult<T>> {

    private final ExtendedQuerySupport extendedQuerySupport;
    private final ServiceProvider serviceProvider;
    private final DbmsDialect dbmsDialect;
    private final Query delegate;
    private final ReturningObjectBuilder<T> objectBuilder;
    private final List<Query> participatingQueries;
    private final String sql;
    private final int firstResult;
    private final int maxResults;

    public CustomReturningModificationQueryPlan(ExtendedQuerySupport extendedQuerySupport, ServiceProvider serviceProvider, Query delegate, ReturningObjectBuilder<T> objectBuilder, List<Query> participatingQueries, String sql, int firstResult, int maxResults) {
        this.extendedQuerySupport = extendedQuerySupport;
        this.serviceProvider = serviceProvider;
        this.dbmsDialect = serviceProvider.getService(DbmsDialect.class);
        this.delegate = delegate;
        this.objectBuilder = objectBuilder;
        this.participatingQueries = participatingQueries;
        this.sql = sql;
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    @Override
    public int executeUpdate() {
        Query baseQuery = participatingQueries.get(0);
        baseQuery.setFirstResult(firstResult);
        baseQuery.setMaxResults(maxResults);
        ReturningResult<Object[]> result = extendedQuerySupport.executeReturning(serviceProvider, participatingQueries, delegate, sql);
        return result.getUpdateCount();
    }

    @Override
    public List<ReturningResult<T>> getResultList() {
        return Arrays.asList(getSingleResult());
    }

    @Override
    public ReturningResult<T> getSingleResult() {
        Query baseQuery = participatingQueries.get(0);
        baseQuery.setFirstResult(firstResult);
        baseQuery.setMaxResults(maxResults);
        // TODO: hibernate will return the object directly for single attribute case instead of an object array
        ReturningResult<Object[]> result = extendedQuerySupport.executeReturning(serviceProvider, participatingQueries, delegate, sql);
        final List<Object[]> originalResultList = result.getResultList();
        final int updateCount = result.getUpdateCount();
        return new DefaultReturningResult<T>(originalResultList, updateCount, dbmsDialect, objectBuilder);
    }
}
