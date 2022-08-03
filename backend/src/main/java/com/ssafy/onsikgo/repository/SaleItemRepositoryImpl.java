package com.ssafy.onsikgo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.onsikgo.entity.SaleItem;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.onsikgo.entity.QSaleItem.saleItem;

public class SaleItemRepositoryImpl implements SaleItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public SaleItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<SaleItem> findSaleItemKeyword(String keyword,String date) {
        return queryFactory.select(saleItem)
                .from(saleItem)
                .where(saleItem.item.itemName.contains(keyword),saleItem.sale.date.eq(date))
                .fetch();
    }
}
