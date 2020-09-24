package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Recorder;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/recorder")
public class RecorderController extends AbstractController<Recorder> {

	public RecorderController() {
		super(Recorder.class);

	}

}
