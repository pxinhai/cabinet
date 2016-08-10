package cabinet.domain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseRepository<T>
{
	protected SessionFactory sessionFac;

	public void setSessionFactory(SessionFactory session)
	{
		this.sessionFac = session;
	}
}
