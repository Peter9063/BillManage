package DongYu.WebBase.System.Utils;


import DongYu.WebBase.System.Entity.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpHelpTools {
    private Logger logger = LoggerFactory.getLogger(HttpHelpTools.class);

    public static String getSession(Site site, String doUri, RestTemplate restTemplate) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

      params.add("usercode", site.getMd5Name());
        params.add("password", site.getMd5PassWord());
        params.add("lcode", site.getLCode());
        params.add("locale", "zh_CN");
        params.add("remember-me", "false");
        return getContent(site, doUri, params, restTemplate);
    }

    @Deprecated
    public static String getContent(Site site, String doUrl, MultiValueMap<String, String> params, RestTemplate restTemplate) {
        String uri = site.getUri() + doUrl;
        HttpEntity<String> reponse = restTemplate.postForEntity(uri, params, String.class);

        return reponse.getHeaders().get("Set-Cookie").toString();
    }


    public static String getContent(Site site, String doUrl, MultiValueMap<String, String> params, MultiValueMap<String, String> headers, RestTemplate restTemplate) {
        String uri = site.getUri() + doUrl;
        HttpEntity<Object> request = new HttpEntity<Object>(params, headers);
        return restTemplate.postForObject(uri, request, String.class);
    }



   
}
