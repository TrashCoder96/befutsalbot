package ru.bifutsal.dao.befutsal;

import ru.bifutsal.dao.befutsal.bo.TeamBo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by itimofeev on 25.11.2017.
 */
@Component
public interface IBefutsalDao {

	List<TeamBo> getTeams();

}
