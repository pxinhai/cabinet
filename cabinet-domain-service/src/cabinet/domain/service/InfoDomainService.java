package cabinet.domain.service;

import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.BytesRefBuilder;
import org.apache.lucene.util.NumericUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabinet.domain.model.Info;
import cabinet.domain.repository.InfoRepository;
import cabinet.infrastructure.CharacterUtility;
import cabinet.infrastructure.ResourceUtility;

@Service
public class InfoDomainService {

	@Autowired
	private InfoRepository infoHome;
	
	public  String getIndexDir()
	{
		return ResourceUtility.getBaseDirectory()+"lucene/";
	}
	
	public void save(Info model)
	{
	 this.infoHome.save(model);
		
		try {
			String textContent=CharacterUtility.delHTMLTag(model.getContent());
			Directory dir = FSDirectory.open(Paths.get(this.getIndexDir()));
			Analyzer luceneAnalyzer = new SmartChineseAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(luceneAnalyzer);
			IndexWriter indexWriter = new IndexWriter(dir, iwc);
			Document doc = new Document();
			doc.add(new TextField("title",model.getTitle(), Field.Store.YES));
			doc.add(new IntField("id",model.getArticleId(),Field.Store.YES));	
			doc.add(new TextField("content",textContent,Field.Store.NO));
			doc.add(new StoredField("description",textContent.substring(0,500)));
			if(model.getArticleId()==0)
			{
			indexWriter.addDocument(doc);
			}
			else
			{
				BytesRefBuilder bytesRefBuilder=new BytesRefBuilder();
				BytesRef bytesRef=new BytesRef(NumericUtils.BUF_SIZE_INT);
				bytesRefBuilder.append(bytesRef);
				NumericUtils.intToPrefixCoded(model.getArticleId(), 0, bytesRefBuilder);
				Term term=new Term("id", bytesRefBuilder);			
				indexWriter.updateDocument(term, doc);
			}
			indexWriter.close();
			dir.close();
			// long startTime = new Date().getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void delete(int id)
	{
		this.infoHome.Delete(id);
	}
	
	public List<Info> getQuery(int categoryid)
	{
		return this.infoHome.getQuery(categoryid);
	}
	
	public Info getModel(int id)
	{
		return this.infoHome.getModel(id);
	}
}
