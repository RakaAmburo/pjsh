package ar.project.wclie.articles;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("articlesController")
public class ArticlesController {
	
	@Value("${remote.articles}")
	private String serviceURL;
	
	public List<ArticleType> getAll() throws MalformedURLException{
		URL url = new URL(serviceURL);
		ArticleService service = new ArticleService(url);
		ArticleServicePT port = service.getArticleServicePTPort();
		List<ArticleType> lista = port.getAll();
		return lista;
	}

}
