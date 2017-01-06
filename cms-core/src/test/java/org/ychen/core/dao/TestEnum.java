package org.ychen.core.dao;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.ychen.basic.util.EnumUtils;
import org.ychen.cms.model.ChannelType;
import org.ychen.cms.model.Group;
import org.ychen.cms.model.RoleType;
import org.ychen.cms.model.User;

import javax.persistence.ManyToOne;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by cy on 16/12/15.
 */
public class TestEnum {

    @Test
    public void testEnumList(){
        List<Integer> list = EnumUtils.enum2Ordinal(RoleType.class);
        for (Integer i : list){
            System.out.println(i);
        }

        List<String> strings = EnumUtils.enum2Name(RoleType.class);
        for (String i : strings){
            System.out.println(i);
        }

        Map<Integer,String> map = EnumUtils.enum2BasicMap(RoleType.class);
        Set<Integer> keys = map.keySet();
        for (Integer i : keys){
            System.out.println(i + ":" + map.get(i));
        }
    }

    @Test
    public void testEnumProp() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println(EnumUtils.enumProp2List(ChannelType.class,"name"));
        System.out.println(EnumUtils.enumProp2OrdinalMap(ChannelType.class,"name"));
        System.out.println(EnumUtils.enumProp2NameMap(ChannelType.class,"name"));
    }
}
