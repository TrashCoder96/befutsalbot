package ru.bifutsal.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.bifutsal.dao.TeamDto;
import ru.bifutsal.dao.befutsal.bo.TeamBo;
import ru.bifutsal.dao.befutsal.impl.BefutsalDao;
import ru.bifutsal.dao.repository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by asus-pc on 17.12.2017.
 */
@Component
public class BefutsalUpdateJobs {

    private static final Logger logger = LoggerFactory.getLogger(BefutsalUpdateJobs.class);

    @Autowired
    private BefutsalDao befutsalDao;

    @Autowired
    private TeamRepository teamRepository;

    @Scheduled(fixedDelay = 10000)
    @Transactional
    private void updateTeams() {
        logger.info("Updating teams in database...");
        List<TeamBo> teamBoList = befutsalDao.getTeams();
        teamBoList.forEach(teamBo -> {
            TeamDto teamDto = teamRepository.findByExternalId(teamBo.getId_team().toString());
            logger.info("externalId = {}", teamBo.getId_team());
            if (teamDto == null) {
                teamDto = new TeamDto();
            }
            teamDto.setExternalId(teamBo.getId_team().toString());
            teamDto.setName(teamBo.getTitle());
            teamRepository.save(teamDto);
        });
        List<Integer> externalTeamIds = teamBoList.stream().map(TeamBo::getId_team).collect(Collectors.toList());
        teamRepository.findAll().forEach(teamDto -> {
           if (!externalTeamIds.contains(Integer.parseInt(teamDto.getExternalId()))) {
               teamRepository.delete(teamDto);
               logger.info("Deleting team with externalId = {} from database", teamDto.getExternalId());
           }
        });
        logger.info("Updating successful");
    }

}
