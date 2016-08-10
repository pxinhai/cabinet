package cabinet.application;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cabinet.domain.model.Info;
import cabinet.domain.service.InfoDomainService;
import cabinet.infrastructure.ResourceUtility;

@Component
public class SearchService {
	
	@Autowired InfoDomainService infoDomainService;
	
	public List<Info> search(String keywords) {
		Analyzer analyzer = new SmartChineseAnalyzer();
		List<Info> rVal=new ArrayList<Info>() {
		};
		try {
			Directory dir = FSDirectory.open(Paths.get(infoDomainService.getIndexDir()));
			IndexReader reader = DirectoryReader.open(dir);
			IndexSearcher is = new IndexSearcher(reader);
			Builder Bquery =new  BooleanQuery.Builder();
			MultiFieldQueryParser  parser=new MultiFieldQueryParser(new String[]{"title","content"},analyzer);			
			parser.setDefaultOperator(QueryParser.AND_OPERATOR);
			parser.setPhraseSlop(10);
			Query keywordQuery = parser.parse(keywords);
			Bquery.add(keywordQuery,BooleanClause.Occur.MUST);
			
			TopDocs topDocs = is.search(Bquery.build(), 1000);
			// System.out.println("总共匹配多少个：" + topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;
			// 应该与topDocs.totalHits相同
			// System.out.println("多少条数据：" + hits.length);
			for (ScoreDoc scoreDoc : hits) {
				// System.out.println("匹配得分：" + scoreDoc.score);
				//System.out.println("文档索引ID：" + scoreDoc.doc);
				Document document = is.doc(scoreDoc.doc);
				Info i=new Info();
				i.setTitle(document.get("title"));
				i.setArticleId(Integer.parseInt(document.get("id")));
				i.setContent(document.get("description"));
				rVal.add(i);
				//System.out.println(document.get("title"));
			}
			reader.close();
			dir.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rVal;
	}

}
