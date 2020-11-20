package com.puci.qs.myspace.utils;

public class KotinDigitHandle {

    public static boolean isDigitEquals(String var1, String var2) {
        try {
            double v1 = Double.parseDouble(var1);
            double v2 = Double.parseDouble(var2);
            return v1 == v2;
        } catch (Exception e) {
            return false;
        }
    }

//    private fun idEquals(id: String): Boolean {
//        if (TextUtils.isEmpty(id) && (
//                !this::mCurDpisodeDetail.isInitialized || mCurDpisodeDetail == null
//                || mCurDpisodeDetail.episode == null
//                || TextUtils.isEmpty(mCurDpisodeDetail.episode!!.id))
//        ) {
//            return false
//        }
//        if (TextUtils.isEmpty(id)) {
//            return true
//        }
//        if (!this::mCurDpisodeDetail.isInitialized || mCurDpisodeDetail == null
//                || mCurDpisodeDetail.episode == null
//                || TextUtils.isEmpty(mCurDpisodeDetail.episode!!.id)) {
//            return true
//        }
//        var idInt: Int = id.toInt()
//        var idCur: Int = mCurDpisodeDetail.episode!!.id!!.toInt()
//        if (idInt == idCur) {
//            return true
//        }
//
//        return false
//
////        return mCurDpisodeDetail.episode?.id.equals(id)
//    }
}
