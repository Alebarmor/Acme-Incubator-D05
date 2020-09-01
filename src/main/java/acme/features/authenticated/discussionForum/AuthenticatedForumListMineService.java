
package acme.features.authenticated.discussionForum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumListMineService implements AbstractListService<Authenticated, Forum> {

	@Autowired
	AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("forumId", entity.getId());
		request.unbind(entity, model, "title" /* , "amount" */);
	}

	@Override
	public Collection<Forum> findMany(final Request<Forum> request) {
		assert request != null;

		String username = request.getPrincipal().getUsername();
		Collection<Forum> result = new ArrayList<>();
		Collection<Forum> forums = this.repository.findManyAll();

		for (Forum f : forums) {
			List<String> usernames = Arrays.asList(f.getUsers().split(","));
			if (usernames.contains(username)) {
				result.add(f);
			}
		}
		return result;
	}

}
