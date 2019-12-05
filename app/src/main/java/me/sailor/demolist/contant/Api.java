package me.sailor.demolist.contant;


import io.reactivex.Observable;
import me.sailor.demolist.bean.JsonRootBean;
import retrofit2.http.GET;

/**
 * @author Administrator on2019/2/13 16:18
 * @desc
 */
public interface Api {
    @GET("random/data/福利/10")
    Observable<JsonRootBean> getImgs();

    @GET("random/data/福利/5")
    Observable<JsonRootBean> get5Imgs();
}
