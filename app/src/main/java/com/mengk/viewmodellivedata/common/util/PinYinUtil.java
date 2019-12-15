package com.mengk.viewmodellivedata.common.util;

import android.text.TextUtils;
import android.util.Log;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Desc
 * Date 2019/12/11
 * author mengk
 */
public class PinYinUtil {

    /**
     * 汉字转拼音
     * 阿尔弗雷多·莫雷洛斯 pinyin = aerfuleiduo·moleiluosi
     * 阿尔及利亚 pinyin = aerjiliya
     * 阿根廷 pinyin = agenting
     * 阿甲 pinyin = ajia
     * 埃里克森· 克里斯蒂安 pinyin = ailikesen· kelisidian
     * 澳超 pinyin = aochao
     * 巴甲 pinyin = bajia
     * 巴塞罗那 pinyin = basailuona
     * 贝西克塔斯 pinyin = beixiketasi
     * 本拉姆里·贾梅尔 pinyin = benlamuli·jiameier
     *
     * 调用方式
     *
     * List对象列表按字母排序
     *
     * public void sortDataListByLetter(List<CusDataBean> dataList) {
     *         //添加数据之后 按字母排序
     *         Collections.sort(dataList, new Comparator<CusDataBean>() {
     *             @Override
     *             public int compare(CusDataBean o1, CusDataBean o2) {
     *                 return PinYinUtil.chineseToSpell(o1.getCnName()).compareToIgnoreCase(PinYinUtil.chineseToSpell(o2.getCnName()));
     *             }
     *         });
     *     }
     *
     * @param chinese
     * @return
     */
    public static String chineseToSpell(String chinese) {
        String pinyinName = "";
        try {
            pinyinName = PinyinHelper.convertToPinyinString(chinese, "", PinyinFormat.WITHOUT_TONE);
            Log.e("===v", "chinese = " + chinese + " pinyin = " + pinyinName);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return pinyinName;
    }


}
