package com.winning.util;

import net.sf.json.JSONObject;



public class JsonUtil
{
    
	/**
     * 为操作成功返回Json
     * 
     * @param strData
     * @return
     */
    public static String returnSuccessJson(String strData, String message)
    {
        StringBuffer returnJson = new StringBuffer("{ \"code\" : 1, \"data\" :");
        returnJson.append(strData);
        returnJson.append(",\"message\": \"");
        returnJson.append(message);
        returnJson.append("\"}");
        return returnJson.toString();
    }


    /**
     * 为操作失败返回Json
     * 
     * @param strData
     * @return
     */
    public static String returnFailureJson(String strData, String errCode)
    {
        StringBuffer returnJson = new StringBuffer("{ \"code\" : 0, \"data\" :[],\"err\": \"");
        returnJson.append(strData);
        returnJson.append("\",\"errCode\": \"");
        returnJson.append(errCode);
        returnJson.append("\"}");
        return returnJson.toString();
    }
    
    public static String getValueByKey(String myParam,String key)
	{
	    if (myParam != null)
        {
	    	JSONObject jsonReq=JSONObject.fromObject(myParam);
	    	if (jsonReq.containsKey(key))
            {
                return jsonReq.getString(key);
            }
            else
            {
                return "";
            }
        }
        else
        {
            return null;
        }
	}
}