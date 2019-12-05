package me.sailor.libcommon.preview;

import java.util.ArrayList;
import java.util.List;

/**
 * -description:
 * -author: created by tang on 2019/10/22 16:07
 */
public interface DownClickListener {
    void downAll(ArrayList<CharSequence> arrayList);

    void downSigle(String url);
}
