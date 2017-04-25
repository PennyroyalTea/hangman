package ru.ioffe.school.hangman;


import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	String getPage(String idString, String moveString) throws IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		int id;
		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			return PageGenerator.newGame();
		}
		Model model;
		try {
			model = new Model(datastore.get(KeyFactory.createKey(Settings.entityName, id)));
		} catch (EntityNotFoundException e) {
			model = new Model();
		}
		if (moveString != null) {
			model.makeMove(moveString.toLowerCase().charAt(0));
		}
		datastore.put(model.toEntity(id));
		if (model.isOver()) {
			datastore.delete(KeyFactory.createKey(Settings.entityName, id));
			
			return PageGenerator.endGame(model);
		}
		return PageGenerator.continueGame(model, id);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String idString = req.getParameter(Settings.URLPropertyId);
		String move = req.getParameter(Settings.URLPropertyMove);

		String page = getPage(idString, move);

		resp.setContentType("text/html");
		resp.getWriter().print(page);
	}
}
