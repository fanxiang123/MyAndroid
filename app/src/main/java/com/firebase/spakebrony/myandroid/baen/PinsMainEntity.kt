package com.firebase.spakebrony.myandroid.baen


/**
 * Created by LiCola on  2016/03/22  20:14
 * 多数网络返会都是使用 这个类
 * 配合AvatarConverter使用 正则转 Object avatar 为String
 */
class PinsMainEntity {
     var pin_id = 0
     var user_id = 0
     var board_id = 0
     var file_id = 0
     var file_url:String = ""
     var file_url_w = 560
     var file_url_h = 1440

    /**
     * farm : farm1
     * bucket : hbimg
     * key : a2945bdf440f492c5144d24eebe45f23b82f3ff84d81f-4vhC2L
     * type : image/jpeg /type=image/gif
     * width : 900
     * height : 1350
     * frames : 1
     */
    //     PinsFileEntity file;
     var media_type = 0
     var source: String? = null
     var link: String? = null
     var raw_text: String = "raw_text"
     var via = 0
     var via_user_id = 0
     var original = 0
     var created_at = 0
     var like_count = 0
     var seq = 0
     var comment_count = 0
     var repin_count = 0
     var is_ = 0
     var orig_source: String? = null
     var liked = false

    /**
     * user_id : 17344184
     * username : 蓝枫芊柔
     * urlname : y0ogn4uezm
     * created_at : 1432372599
     * avatar : https
     */
    //     PinsUserEntity user;
    //    /**
    //     * board_id : 24048815
    //     * user_id : 17344184
    //     * title : 爱好
    //     * description :
    //     * category_id : food_drink
    //     * seq : 5
    //     * pin_count : 1128
    //     * follow_count : 76
    //     * like_count : 3
    //     * created_at : 1432544067
    //     * updated_at : 1450779767
    //     * deleting : 0
    //     * is_ : 0
    //     * extra : {"cover":{"pin_id":"401845965"}}
    //     */
    //
    //     PinsBoardEntity board;
    //
    //    /**
    //     * user_id : 605533
    //     * username : 宁馨郁金香
    //     * urlname : xxf837568038
    //     * created_at : 1344088961
    //     * avatar : https
    //     */

}