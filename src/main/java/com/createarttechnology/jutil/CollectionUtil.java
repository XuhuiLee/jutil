package com.createarttechnology.jutil;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by lixuhui on 2018/9/18.
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static <I, O> List<O> transformList(List<I> inputList, Function<I, O> function) {
        if (inputList == null)
            return null;

        List<O> outputList = Lists.newArrayListWithExpectedSize(inputList.size());
        if (inputList.size() == 0)
            return Lists.newArrayList();

        for (I inputItem : inputList) {
            if (inputItem != null) {
                O outputItem = function.apply(inputItem);
                if (outputItem != null) {
                    outputList.add(outputItem);
                }
            }
        }

        return outputList;
    }

}
