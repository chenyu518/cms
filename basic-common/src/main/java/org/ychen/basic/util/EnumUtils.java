package org.ychen.basic.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cy on 16/12/15.
 *
 * @author   <a href="mailto:yu.chen@ozstrategy.com">Yu Chen</a>
 * @version  12/16/2016 14:18
 */
public class EnumUtils {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * enum2BasicMap.
   *
   * @param   clz  Class
   *
   * @return  Map
   */
  public static Map<Integer, String> enum2BasicMap(Class<? extends Enum> clz) {
    if (!clz.isEnum()) {
      return null;
    }

    Enum[]               enums = clz.getEnumConstants();
    Map<Integer, String> map   = new HashMap<Integer, String>();

    for (Enum e : enums) {
      map.put(e.ordinal(), e.name());
    }

    return map;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * enum2Name.
   *
   * @param   clz  Class
   *
   * @return  List
   */
  public static List<String> enum2Name(Class<? extends Enum> clz) {
    if (!clz.isEnum()) {
      return null;
    }

    Enum[]       enums     = clz.getEnumConstants();
    List<String> enumNames = new ArrayList<String>();

    for (Enum e : enums) {
      enumNames.add(e.name());
    }

    return enumNames;

  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * enum2Ordinal.
   *
   * @param   clz  Class
   *
   * @return  List
   */
  public static List<Integer> enum2Ordinal(Class<? extends Enum> clz) {
    if (!clz.isEnum()) {
      return null;
    }

    Enum[]        enums    = clz.getEnumConstants();
    List<Integer> enumKeys = new ArrayList<Integer>();

    for (Enum e : enums) {
      enumKeys.add(e.ordinal());
    }

    return enumKeys;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 将枚举中的值的某个属性转换为字符串列表.
   *
   * @param   clz       $param.type$
   * @param   propName  $param.type$
   *
   * @return  将枚举中的值的某个属性转换为字符串列表.
   */
  public static List<String> enumProp2List(Class<? extends Enum> clz, String propName) {
    if (!clz.isEnum()) {
      return null;
    }

    try {
      Enum[]       enums = clz.getEnumConstants();
      List<String> rels  = new ArrayList<String>();

      for (Enum en : enums) {
        rels.add((String) PropertyUtils.getProperty(en, propName));
      }

      return rels;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return null;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 将枚举中的两个属性转换为map.
   *
   * @param   clz        $param.type$
   * @param   keyProp    要转化的key的属性名称
   * @param   valueProp  要转换的value的属性名称
   *
   * @return  将枚举中的两个属性转换为map.
   */
  public static Map<String, String> enumProp2Map(Class<? extends Enum> clz, String keyProp, String valueProp) {
    if (!clz.isEnum()) {
      return null;
    }

    try {
      Enum[]              enums = clz.getEnumConstants();
      Map<String, String> rels  = new HashMap<String, String>();

      for (Enum en : enums) {
        rels.put((String) PropertyUtils.getProperty(en, keyProp), (String) PropertyUtils.getProperty(en, valueProp));
      }

      return rels;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return null;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 将枚举中的值的某个属性转换为名称和字符串map.
   *
   * @param   clz       $param.type$
   * @param   propName  $param.type$
   *
   * @return  将枚举中的值的某个属性转换为名称和字符串map.
   */
  public static Map<String, String> enumProp2NameMap(Class<? extends Enum> clz, String propName) {
    if (!clz.isEnum()) {
      return null;
    }

    try {
      Enum[]              enums = clz.getEnumConstants();
      Map<String, String> rels  = new HashMap<String, String>();

      for (Enum en : enums) {
        rels.put(en.name(), (String) PropertyUtils.getProperty(en, propName));
      }

      return rels;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return null;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * 将枚举中的值的某个属性转换为序号和字符串列表.
   *
   * @param   clz       $param.type$
   * @param   propName  $param.type$
   *
   * @return  将枚举中的值的某个属性转换为序号和字符串列表.
   */
  public static Map<Integer, String> enumProp2OrdinalMap(Class<? extends Enum> clz, String propName) {
    if (!clz.isEnum()) {
      return null;
    }

    try {
      Enum[]               enums = clz.getEnumConstants();
      Map<Integer, String> rels  = new HashMap<Integer, String>();

      for (Enum en : enums) {
        rels.put(en.ordinal(), (String) PropertyUtils.getProperty(en, propName));
      }

      return rels;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return null;
  }

} // end class EnumUtils
