package com.takamol.roboagent.gateway.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takamol.roboagent.gateway.models.LogsVO;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/Furat/write-answer")
@Repository
public interface writeLogsRepository extends JpaRepository<LogsVO, Integer>{
	LocalTime now = LocalTime.now();
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Logs (ID, RequesterIP, Request, Response, CreationDate) VALUES ('2','1','1','1',Null)", nativeQuery = true)
	public void insertLogsVO(LogsVO lVO);
}
