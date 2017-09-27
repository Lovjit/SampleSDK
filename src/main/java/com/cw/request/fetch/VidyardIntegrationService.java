package com.cw.request.fetch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.hermease.camais.client.request.tree.FetchAssetRequest;
import com.hermease.camais.client.response.tree.Asset;
import com.hermease.camais.client.response.tree.AssetTypeResponse;
import com.hermease.camais.client.response.tree.FetchAssetResponse;

public class VidyardIntegrationService {
	
	public static FetchAssetResponse getContentList(FetchAssetRequest fetchAssetRequest) throws ClientProtocolException, URISyntaxException, IOException{
		String authToken = fetchAssetRequest.getAuthToken();
		Map<String,String> getParams = new HashMap<String,String>();
		getParams.put("auth_token", authToken);
		HttpResponse httpResponse = RestUtil.callGetAPI("https", "api.vidyard.com", "/dashboard/v1/videos", getParams);
		HttpEntity resEntity = httpResponse.getEntity();
        String response = EntityUtils.toString(resEntity);
        JSONArray jArr = new JSONArray(response);
        List<Asset> assets = new ArrayList<Asset>();
        Asset asset;
        for(int i=0;i<jArr.length();i++){
        	JSONObject jObj = jArr.getJSONObject(i);
        	String id = String.valueOf(jObj.get("id"));
        	String name = jObj.getString("name");
        	asset = new Asset();
        	asset.setAssetId(id);
        	asset.setAssetName(name);
        	assets.add(asset);
        }
        AssetTypeResponse assetTypeResponse = new AssetTypeResponse();
        assetTypeResponse.setAssetType("video");
        assetTypeResponse.setAssetList(assets);
        List<AssetTypeResponse> assetTypeResponseList = new ArrayList<AssetTypeResponse>();
        assetTypeResponseList.add(assetTypeResponse);
        FetchAssetResponse fetchAssetResponse = new FetchAssetResponse();
        fetchAssetResponse.setAssetTypeResponseList(assetTypeResponseList);
		return fetchAssetResponse;
	}
	
//	public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException{
//		FetchAssetRequest fetchAssetRequest =new FetchAssetRequest();
//		fetchAssetRequest.setAuthToken("z9n0jKAVs2BraQBPO4EE_w");
//		System.out.println(getContentList(fetchAssetRequest).toString());
//	}
	
}
