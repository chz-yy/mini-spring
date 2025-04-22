package beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList=new ArrayList<PropertyValue>();
    public void addPropertyValue(PropertyValue pv) {
        for(int i=0;i<propertyValueList.size();i++) {
            PropertyValue currentPv = propertyValueList.get(i);
            if(currentPv.getName().equals(pv.getName())) {
                this.propertyValueList.set(i, pv);
                return;
            }
        }
        this.propertyValueList.add(pv);
    }
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
    public PropertyValue getPropertyValue(String propertyName) {
        for(int i=0;i<propertyValueList.size();i++) {
            PropertyValue currentPv = propertyValueList.get(i);
            if(currentPv.getName().equals(propertyName)) {
                return currentPv;
            }
        }
        return null;
    }
}
