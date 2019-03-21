package com.resourcemanager.spring.formatter;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.resourcemanager.spring.model.Skill;
import com.resourcemanager.spring.service.SkillService;

/**
 * The Class SkillFormatter.
 */
@Component
public class SkillFormatter implements Formatter<Skill> {

	/** The skill service. */
	@Autowired
	private SkillService skillService;
	// Some service class which can give the Skill after
	// fetching from Database

	/*
	 * (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public Skill parse(String skillId, Locale arg1) throws ParseException {
		return skillService.getSkillById(Integer.parseInt(skillId));
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(Skill skill, Locale arg1) {
		return skill.getName();
	}
}