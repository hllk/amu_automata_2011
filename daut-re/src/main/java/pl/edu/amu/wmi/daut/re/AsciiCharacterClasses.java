package pl.edu.amu.wmi.daut.re;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa pomocnicza zawierająca mapę klas znaków.
 */
public final class AsciiCharacterClasses {
    /**
     * Mapa zawierająca klasy znaków.
     */
    public static final Map<String, String> CLASS_MAP;
    static
    {
        Map<String, String> result = new HashMap<String, String>();
        result.put("alnum", "0-9A-Za-z");
        result.put("alpha", "A-Za-z");
        result.put("blank", "\t ");
        result.put("cntrl", "\u0000-\u001F\u007F");
        result.put("digit", "0-9");
        result.put("graph", "!~-");
        result.put("lower", "a-z");
        result.put("print", " -~");
        result.put("punct", "!-/:-@[-`{-~");
        result.put("space", "\t\n\f\r \u000B");
        result.put("upper", "A-Z");
        result.put("word", "0-9A-Za-z_");
        result.put("xdigit", "0-9A-Fa-f");
        CLASS_MAP = Collections.unmodifiableMap(result);
    }

    private AsciiCharacterClasses() {
    }
}
