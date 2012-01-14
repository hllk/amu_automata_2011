package pl.edu.amu.wmi.daut.re;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.CharClassTransitionLabel;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;

class UnknownUnicodeClassException extends RuntimeException {
}

/**
 * Unicode class.
 */
public class UnicodeClassOperator extends NullaryRegexpOperator {

    private String str;

    private static final Map<String, String> MAP_A_L_UNICODE_CLASS = createMapAL();
    private static final Map<String, String> MAP_M_Z_UNICODE_CLASS = createMapMZ();

    private static Map<String, String> createMapMZ() {

        Map<String, String> result = new HashMap<String, String>();

        result.put("Malayalam", "\u0D00–\u0D7F");
        result.put("Mongolian", "\u1800–\u18AF");
        result.put("Myanmar", "\u1000–\u109F\uAA60–\uAA7B");
        result.put("New Tai Lue", "\u1980–\u19DF");
        result.put("Nko", "\u07C0–\u07FF");
        result.put("Ogham", "\u1680–\u169F");
        result.put("Ol Chiki", "\u1C50–\u1C7F");
        result.put("Old Italic", "\u10300–\u1032F");
        result.put("Old Persian", "\u103A0–\u103DF ");
        result.put("Oriya", "\u0B00–\u0B7F");
        result.put("Osmanya", "\u10480–\u104AF");
        result.put("Phags_Pa", "\uA840–\uA877");
        result.put("Phoenician", "\u10900–\u1091F");
        result.put("Rejang", "\uA930-\uA95F");
        result.put("Runic", "\u16A0–\u16FF");
        result.put("Saurashtra", "\uA880–\uA8DF");
        result.put("Shavian", "\u10450–\u1047F");
        result.put("Sinhala", "\u0D80–\u0DFF");
        result.put("Sundanese", "\u1B80–\u1BBF");
        result.put("Syloti Nagri", "\uA800–\uA82F");
        result.put("Syriac", "\u0700-\u074F");
        result.put("Tagalog", "\u1700–\u171F");
        result.put("Tagbanwa", "\u1760–\u177F");
        result.put("Tai Le", "\u1950–\u197F");
        result.put("Tamil", "\u0B80–\u0BFF");
        result.put("Telugu", "\u0C00–\u0C7F");
        result.put("Thaana", "\u0780–\u07BF");
        result.put("Thai", "\u0E00–\u0E7F");
        result.put("Tibetan", "\u0F00–\u0FFF");
        result.put("Tifinagh", "\u2D30–\u2D7F");
        result.put("Ugaritic", "\u10380–\u1039F");
        result.put("Vai", "\uA500–\uA63F");
        result.put("Yi", "\uA000-\uA48C");

        result.put("Mc", "\u0903\u093B\u093E-\u0940\u0949-\u094C\u094E\u094F\u0982\u0983"
                + "\u09BE-\u09C0\u09C7\u09C8\u09CB\u09CC\u09D7\u0A03\u0A3E-\u0A40\u0A83"
                + "\u0ABE-\u0AC0\u0AC9\u0ACB\u0ACC\u0B02\u0B03\u0B3E\u0B40\u0B47\u0B48\u0B4B"
                + "\u0B4C\u0B57\u0BBE\u0BBF\u0BC1\u0BC2\u0BC6-\u0BC8\u0BCA-\u0BCC\u0BD7"
                + "\u0C01-\u0C03\u0C41-\u0C44\u0C82\u0C83\u0CBE\u0CC0-\u0CC4\u0CC7\u0CC8"
                + "\u0CCA\u0CCB\u0CD5\u0CD6\u0D02\u0D03\u0D3E\u0D3F\u0D40\u0D46-\u0D48"
                + "\u0D4A-\u0D4C\u0D57\u0D82\u0D83\u0DCF\u0DD0\u0DD1\u0DD8-\u0DDF\u0DF2"
                + "\u0DF3\u0F3E\u0F3F\u0F7F\u102B\u102C\u1031\u1038\u103B\u103C\u1056\u1057"
                + "\u1062-\u1064\u1067-\u106D\u1083\u1084\u1087-\u108C\u108F\u109A-\u109C"
                + "\u17B6\u17BE-\u17C5\u17C7\u17C8\u1923-\u1926\u1929-\u192B\u1930\u1931"
                + "\u1933-\u1938\u19B0-\u19C0\u19C8\u19C9\u1A19-\u1A1B\u1A55\u1A57\u1A61"
                + "\u1A63\u1A64\u1A6D-\u1A72\u1B04\u1B35\u1B3B\u1B3D-\u1B3F\u1B40\u1B41"
                + "\u1B43\u1B44\u1B82\u1BA1\u1BA6\u1BA7\u1BAA\u1BE7\u1BEA-\u1BEC\u1BEE"
                + "\u1BF2\u1BF3\u1C24-\u1C2B\u1C34\u1C35\u1CE1\u1CF2\uA823\uA824\uA827\uA880"
                + "\uA881\uA8B4-\uA8C3\uA952\uA953\uA983\uA9B4\uA9B5\uA9BA\uA9BB\uA9BD-\uA9BF"
                + "\uA9C0\uAA2F\uAA30\uAA33\uAA34\uAA4D\uAA7B\uABE3\uABE4\uABE6\uABE7\uABE9"
                + "\uABEA\uABEC\u11000\u11002\u11082\u110B0-\u110B2\u110B7\u110B8\u1D165"
                + "\u1D166\u1D16D-\u1D172");
        result.put("Me", "\u0488\u0489\u20DD-\u20E4\uA670-\uA672");
        result.put("Nd", "\u0030-\u0039\u0660-\u0669\u06F0-\u06F9\u07C0-\u07C9"
                + "\u0966-\u096F\u09E6-\u09EF\u0A66-\u0A6F\u0AE6-\u0AEF\u0B66-\u0B6F"
                + "\u0BE6-\u0BEF\u0C66-\u0C6F\u0CE6-\u0CEF\u0D66-\u0D6F\u0E50-\u0E59"
                + "\u0ED0-\u0ED9\u0F20-\u0F29\u1040-u1049\u1090-\u1099\u17E0-\u17E9"
                + "\u1810-\u1819\u1946-\u194F\u19D0-\u19D9\u1A80-\u1A89\u1A90-\u1A99"
                + "\u1B50-\u1B59\u1BB0-\u1BB9\u1C40-\u1C49\u1C50-\u1C59\uA620-\uA629"
                + "\uA8D0-\uA8D9\uA900-\uA909\uA9D0-\uA9D9\uAA50-\uAA59\uABF0-\uABF9"
                + "\uFF10-\uFF19\u104A0-\u104A9\u11066-\u1106F\u1D7CE-\u1D7FF");
        result.put("Nl", "\u16EE-\u16F0\u2160-\u2182\u2185-\u2188\u3007\u3021-\u3029"
                + "\u3038-\u303A\uA6E6-\uA6EF\u10140-\u10174\u10341\u1034A\u103D1-\u103D5"
                + "\u12400-\u12462");
        result.put("No", "\u00B2\u00B3\u00B9\u00BC-\u00BE\u09F4-\u09F9\u0B72-\u0B77"
                + "\u0BF0-\u0BF2\u0C78-\u0C7E\u0D70-\u0D75\u0F2A-\u0F33\u1369-\u137C"
                + "\u17F0-\u17F9\u19DA\u2070\u2074-\u2079\u2080-\u2089\u2150-\u215F"
                + "\u2189\u2460-\u249B\u24EA-\u24FF\u2776-\u2793\u2CFD\u3192-\u3195"
                + "\u3220-\u3229\u3251-\u325F\u3280-\u3289\u32B1-\u32BF\uA830-\uA835"
                + "\u10107-\u10133\u10175-\u10178\u1018A\u10320-\u10323\u10858-\u1085F"
                + "\u10916-\u1091B\u10A40-\u10A47\u10A7D\u10A7E\u10B58-\u10B5F"
                + "\u10B78-\u10E7E\u11052-\u11065\u1D360-\u1D371\u1F100-\u1F10A");
        result.put("Pc", "\u005F\u203F\u2040\u2054\uFE33\uFE34\uFE4D-\uFE4F\uFF3F");
        result.put("Pd", "\u002D\u058A\u05BE\u1400\u1806\u2010-\u2015\u2E17\u2E1A"
                + "\u301C\u3030\u30A0\uFE31\uFE32\uFE58\uFE63\uFF0D");
        result.put("Pe", "\u0029\u005D\u007D\u0F3B\u0F3D\u169C\u2046\u207E\u208E\u232A"
                + "\u2769\u276B\u276D\u276F\u2771\u2773\u2775\u27C6\u27E7\u27E9\u27EB"
                + "\u27ED\u27EF\u2984\u2986\u2988\u298A\u298C\u298E\u2990\u2992\u2994"
                + "\u2996\u2998\u29D9\u29DB\u29FD\u2E23\u2E25\u2E27\u2E29\u3009\u300B"
                + "\u300D\u300F\u3011\u3015\u3017\u3019\u301B\u301E\u301F\uFD3F\uFE18"
                + "\uFE36\uFE38\uFE3A\uFE3C\uFE3E\uFE40\uFE42\uFE44\uFE48\uFE5A\uFE5C"
                + "\uFE5E\uFF09\uFF3D\uFF5D\uFF60\uFF63");
        result.put("Pf", "\u00BB\u2019\u201D\u203A\u2E03\u2E05\u2E0A\u2E0D\u2E1D\u2E21");
        result.put("Pi", "\u00AB\u2018\u201B\u201C\u201F\u2039\u2E02\u2E04\u2E09\u2E0C"
                + "\u2E1C\u2E20");
        result.put("Po", "\u0021-\u0023\u0025-\u0027\u002A\u002C\u002E\u002F\u003A\u003B\u003F"
                + "\u0040\u00A1\u00B7\u00BF\u037E\u0387\u055A-\u055F\u0589\u05C0\u05C3"
                + "\u05C6\u05F3\u05F4\u0609\u060A\u060C\u060D\u061B\u061E\u061F\u066A-\u066D"
                + "\u06D4\u0700-\u070D\u07F7-\u07F9\u0830-\u083E\u085E\u0964\u0965\u0970\u0DF4"
                + "\u0E4F\u0E5A\u0E5B\u0F04-\u0F12\u0F85\u0FD0-\u0FD4\u0FD9\u0FDA\u104A-\u104F"
                + "\u10FB\u1361-\u1368\u166D\u166E\u16EB-\u16ED\u1735\u1736\u17D4-\u17D6"
                + "\u17D8-\u17DA\u1800-\u1805\u1807-\u180A\u1944\u1945\u1A1E-\u1AA6"
                + "\u1AA8-\u1AAD\u1B5A-\u1B60\u1BFC-\u1BFF\u1C3B-\u1C3F\u1C7E\u1C7F\u1CD3"
                + "\u2016\u2017\u2020-\u2027\u2030-\u2038\u203B-\u203E\u2041-\u2043"
                + "\u2047-\u2051\u2053\u2055-\u205E\u2CF9-\u2CFC\u2CFE\u2CFF\u2D70\u2E00"
                + "\u2E01\u2E06-\u2E08\u2E0B\u2E0E-\u2E16\u2E18\u2E19\u2E1B\u2E1E\u2E1F"
                + "\u2E2A-\u2E2E\u2E30\u2E31\u3001-\u3003\u303D\u30FB\uA4FE\uA4FF\uA60D"
                + "\uA60E\uA60F\uA673\uA67E\uA6F2-\uA6F7\uA874-\uA877\uA8CE\uA8CF\uA8F8"
                + "\uA8F9\uA8FA\uA92E\uA92F\uA95F\uA9C1-\uA9CD\uA9DE\uA9DF\uAA5C-\uAA5F"
                + "\uAADE\uAADF\uABEB\uFE10-\uFE16\uFE19\uFE30\uFE45\uFE46\uFE49\uFE4A"
                + "\uFE4B\uFE4C\uFE50-\uFE52\uFE54-\uFE57\uFE5F\uFE60\uFE61\uFE68\uFE6A"
                + "\uFE6B\uFF01-\uFF03\uFF05-\uFF07\uFF0A\uFF0C\uFF0E\uFF0F\uFF1A\uFF1B"
                + "\uFF1F\uFF20\uFF3C\uFF61\uFF64\uFF65\u10100\u10101\u1039F\u103D0\u10857"
                + "\u1091F\u1093F\u10A50-\u10A58\u10A7F\u10B39\u10B3A-\u10B3F\u11047\u11048"
                + "\u11049\u1104A-\u1104D\u110BB\u110BC\u110BE-\u110C1\u12470-\u12473");
        result.put("Ps", "\u0028\u005B\u007B\u0F3A\u0F3C\u169B\u201A\u201E\u2045\u207D"
                + "\u208D\u2329\u2768\u276A\u276C\u276E\u2770\u2772\u2774\u27C5\u27E6"
                + "\u27E8\u27EA\u27EC\u27EE\u2983\u2985\u2987\u2989\u298B\u298D\u298F"
                + "\u2991\u2993\u2995\u2997\u29D8\u29DA\u29FC\u2E22\u2E24\u2E26\u2E28"
                + "\u3008\u300A\u300C\u300E\u3010\u3014\u3016\u3018\u301A\u301D\uFD3E"
                + "\uFE17\uFE35\uFE37\uFE39\uFE3B\uFE3D\uFE3F\uFE41\uFE43\uFE47\uFE59"
                + "\uFE5B\uFE5D\uFF08\uFF3B\uFF5B\uFF5F\uFF62");
        result.put("Sc", "\u0024\u00A2-\u00A5\u060B\u09F2\u09F3\u09FB\u0AF1\u0BF9\u0E3F"
                + "\u17DB\u20A0-\u20B9\uA838\uFDFC\uFE69\uFF04\uFFE0\uFFE1\uFFE5\uFFE6");
        result.put("Sk", "\u005E\u0060\u00A8\u00AF\u00B4\u00B8\u02C2-\u02C5\u02D2-\u02DF"
                + "\u02E5-\u02EB\u02ED\u02EF-\u02FF\u0375\u0384\u0385\u1FBD\u1FBF\u1FC0"
                + "\u1FC1\u1FCD-\u1FCF\u1FDD-\u1FDF\u1FED-\u1FEF\u1FFD\u1FFE\u309B\u309C"
                + "\uA700-\uA716\uA720\uA721\uA789\uA78A\uFBB2-\uFBC1\uFF3E\uFF40\uFFE3");
        result.put("Zl", "\u2028");
        result.put("Zp", "\u2029");
        result.put("Zs", "\u0020\u00A0\u1680\u180E\u2000-\u200A\u202F\u205F\u3000");

        return Collections.unmodifiableMap(result);
    }

    private static Map<String, String> createMapAL() {

        Map<String, String> result = new HashMap<String, String>();

        result.put("Arabic", "\u0600-\u06FF\u0750-\u077F\uFB50-\uFDFF\uFE70-\uFEFF"
                + "\u10E60-\u10E7F");
        result.put("Armenian", "\u0530–\u058F\uFB13–\uFB17");
        result.put("Balinese", "\u1B00-\u1B7F");
        result.put("Bengali", "\u0980-\u09FF");
        result.put("Bopomofo", "\u3100-\u312F\u31A0-\u31BF");
        result.put("Braille", "\u2800-\u28FF");
        result.put("Buginese", "\u1A00-\u1A1F");
        result.put("Buhid", "\u1740-\u175F");
        result.put("Canadian Aboriginal", "\u1400-\u167F\u18B0-\u18FF");
        result.put("Carian", "\u102A0-\u102DF");
        result.put("Cham", "\uAA00-\uAA5F");
        result.put("Cherokee", "\u13A0-\u13FF");
        result.put("Coptic", "\u2C80-\u2CFF");
        result.put("Cuneiform", "\u12000–\u1237F\u12400-\u1247F");
        result.put("Cypriot", "\u10800-\u1083F\u10100-\u1013F");
        result.put("Cyrillic", "\u0400–\u04FF\u0500–\u052F\u2DE0–\u2DFF\uA640–\uA69F");
        result.put("Deseret", "\u10400–\u1044F");
        result.put("Devanagari", "\u0900–\u097F\u1CD0–\u1CFF\uA8E0–\uA8FF");
        result.put("Ethiopic", "\u1200-\u137F\u1380-\u139F\u2D80-\u2DDF\uAB00-\uAB2F");
        result.put("Georgian", "\u10A0-\u10FF\u10D0-\u10FF\u10A0-\u10CF\u2D00-\u2D2F");
        result.put("Glagolitic", "\u2C00–\u2C5F");
        result.put("Gothic", "\u10330–\u1034F");
        result.put("Greek", "\u0370-\u03FF\u1F00-\u1FFF");
        result.put("Gujarati", "\u0A80–\u0AFF");
        result.put("Gurmukhi", "\u0A00–\u0A7F");
        result.put("Han", "\u4E00–\u9FFF\u3400–\u4DBF\u20000–\u2A6DF\u2A700–\u2B73F"
                + "\u2B840–\u2B81F");
        result.put("Hangul", "\u1100-\u11FF\u3130-\u318F\uA960-\uA97F\uD7B0-\uD7FF");
        result.put("Hanunoo", "\u1720-\u173F");
        result.put("Hebrew", "\u0590-\u05FF\uFB1D-\uFB4F");
        result.put("Hiragana", "\u3040-\u309F");
        result.put("Kannada", "\u0C80–\u0CFF");
        result.put("Katakana", "\u30A0-\u30FF\u31F0-\u31FF");
        result.put("Kayah Li", "\uA900-\uA92F");
        result.put("Kharoshthi", "\u10A00-\u10A5F");
        result.put("Khmer", "\u1780-\u17FF\u19E0–\u19FF");
        result.put("Lao", "\u0E80–\u0EFF");
        result.put("Latin", "\u0000–\u007F\u0080–\u00FF\u0100–\u017F\u0180–\u024F"
                + "\u0250–\u02AF\u1D00–\u1D7F\u1D80–\u1DBF\u1E00–\u1EFF\u2070-\u209F"
                + "\u2100–\u214F\u2460–\u24FF\u2C60–\u2C7F\uA720–\uA7FF\uFB00–\uFB4F"
                + "\uFF00–\uFFEF\u1D400–\u1D7FF\u1F100–\u1F1FF");
        result.put("Lepcha", "\u1C00–\u1C4F");
        result.put("Limbu", "\u1900–\u194F");
        result.put("Linear B", "\u10000-\u1007F\u10080-\u100FF\u10100-\u1013F");
        result.put("Lycian", "\u10280–\u1029F");
        result.put("Lydian", "\u10920–\u1093F");

        result.put("Cc", "\u0000-\u001F\u007F\u0080-\u009F");
        result.put("Cf", "\u00AD\u0600-\u0603\u06DD\u070F\u17B4\u17B5\u200B-\u200F"
                + "\u202A-\u202E\u2060-\u2064\u206A-\u206F\uFEFF\uFFF9\uFFFA\uFFFB"
                + "\u110BD\u1D173-\u1D17A\uE0001\uE0020-\uE007F");
        result.put("Co", "\uE000\uF8FF\uF0000\uFFFFD\u100000\u10FFFD");
        result.put("Cs", "\uD800\uDB7F\uDB80\uDBFF\uDC00\uDFFF");
        result.put("Lm", "\u02B0-\u02C1\u02C6-\u02D1\u02E0-\u02E4\u02EC\u02EE\u0374"
                + "\u037A\u0559\u0640\u06E5\u06E6\u07F4\u07F5\u07FA\u081A\u0824\u0828"
                + "\u0971\u0E46\u0EC6\u10FC\u17D7\u1843\u1AA7\u1C78\u1C79\u1C7A-\u1C7D"
                + "\u1D2C-\u1D61\u1D78\u1D9B-\u1DBF\u2071\u207F\u2090-\u209C\u2C7D"
                + "\u2D6F\u2E2F\u3005\u3031-\u3035\u303B\u309D\u309E\u30FC\u30FD"
                + "\u30FE\uA015\uA4F8-\uA4FD\uA60C\uA67F\uA717-\uA71F\uA770\uA788\uA9CF"
                + "\uAA70\uAADD\uFF70\uFF9E\uFF9F");
        result.put("Lt", "\u01C5\u01C8\u01CB\u01F2\u1F88-\u1F8F\u1F98-\u1F9F\u1FA8-\u1FAF"
                + "\u1FBC\u1FCC\u1FFC");


        return Collections.unmodifiableMap(result);
    }

    /**
     * konstruktor Unicode classes.
     */
    public UnicodeClassOperator(String a) {
        if (a.substring(0, 1).equals("\\p"))
            a = a.substring(2);
        if (a.substring(0, 0).equals("{"))
            a = a.substring(1);
        if (a.endsWith("}"))
            a = a.substring(0, a.length() - 1);

        str = MAP_A_L_UNICODE_CLASS.get(a);
        if (str == null)
            str = MAP_M_Z_UNICODE_CLASS.get(a);
        if (str == null)
            throw new UnknownUnicodeClassException();
    }

    /**
     * Generuje automat.
     */
    @Override
    public AutomatonSpecification createFixedAutomaton() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        automaton.addTransition(q0, q1, new CharClassTransitionLabel(str));

        return automaton;
    }

    /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            return new UnicodeClassOperator(params.get(0));
        }

        @Override
        public int numberOfParams() {
            return 1;
        }
    }
}

