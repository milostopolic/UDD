package com.ftn.es.service;

import com.ftn.es.dto.NewWorkDTO;
import com.ftn.es.model.NaucniRad;

public interface WorkService {
	NaucniRad napraviRad(NewWorkDTO nw);
	void indeksiraj(NaucniRad nr);
}
