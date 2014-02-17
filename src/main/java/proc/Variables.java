package proc;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/**
 * User: def
 * Date: 2/17/14
 * Time: 9:51 PM
 */
@Resource.Classpath("properties")
public class Variables {

    public Variables() {
        PropertyLoader.populate(this); //инициализация полей класса значениями из файла
    }

    @Property("cardBin")
    private String cardBin = "";

    public String[] getCardBins(){
        return cardBin.split(",");
    }
}
