package de.jformchecker;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class Utils {
  public static void fillBean(Map<String, FormCheckerElement> elements, Object bean) {
    Iterator<String> it = elements.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      if (PropertyUtils.isWriteable(bean, key)) {
        try {
          // TODO: Destinguish between Strings/Dates/Boolean...
          PropertyUtils.setSimpleProperty(bean, key, elements.get(key).getValue());
        } catch (Exception e) {
          System.err.println("problem with setting bean-property:" + key);
        }
      }
    }
  }
}
