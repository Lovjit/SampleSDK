package com.cw.request.fetch;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.client.ClientProtocolException;
import com.cw.main.FetchContentIntegration;
import com.hermease.camais.client.request.tree.FetchAssetRequest;
import com.hermease.camais.client.response.tree.FetchAssetResponse;


public class FetchAssetService implements FetchContentIntegration{
	
	public FetchAssetResponse fetchContent(FetchAssetRequest fetchAssetRequest){
		try {
			VidyardIntegrationService.getContentList(fetchAssetRequest);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
