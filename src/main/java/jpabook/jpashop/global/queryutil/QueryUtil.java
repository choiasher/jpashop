package jpabook.jpashop.global.queryutil;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryUtil {

    public static <T extends Enum<T>> BooleanExpression pathEqual(EnumPath<T> path, T val) {
        return val != null ? path.eq(val) : null;
    }

    public static BooleanExpression pathLike(StringPath path, String val) {
        return val != null ? path.like(val) : null;
    }

}
