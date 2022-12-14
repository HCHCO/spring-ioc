package bean.property;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPorpertyValue(PropertyValue pv){
        propertyValueList.add(pv);
    }
    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue pv: propertyValueList){
            if(pv.getName() == propertyName){
                return pv;
            }
        }
        return  null;
    }
}
