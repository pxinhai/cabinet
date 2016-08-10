package cabinet.web.manager.controller;

import java.util.Map;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cabinet.application.SearchService;
import cabinet.infrastructure.CharacterUtility;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

@Controller
@RequestMapping("/manager/search")
public class SearchController extends BaseController {

	@Autowired
	private SearchService serarchHome;

	@RequestMapping()
	public String home(Map<String, Object> map) {
		String keyword = this.request.getParameter("key");
		try {
			keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception exception) {
		}
		if (!CharacterUtility.IsNullOrWhiteSpace(keyword)) {
			map.put("dataList", this.serarchHome.search(keyword));
		}
		return "manager/search/home";
	}
}
