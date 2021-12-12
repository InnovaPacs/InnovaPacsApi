package innova.pacs.api.model.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface IInnovaFileService {
	/**
	 * Method use to create the html template with all the values
	 * @param template
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @throws IOException
	 */
	public String setData(String template, Object dto) throws IOException;
	
	/**
	 * Method use to create PDF file
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @throws IOException
	 */
	public ByteArrayInputStream preview(Object dto) throws IOException;
	
	/**
	 * Method used to test generating file
	 * @return
	 * @throws ServiceException
	 * @throws IOException
	 */
	public ByteArrayInputStream testService() throws IOException;
	
	
}
