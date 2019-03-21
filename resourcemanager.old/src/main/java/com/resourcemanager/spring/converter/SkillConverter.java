package com.resourcemanager.spring.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.resourcemanager.spring.model.Skill;
import com.resourcemanager.spring.service.SkillService;

/**
 * The Class SkillConverter.
 */
@Component
public class SkillConverter implements Converter<String, Skill> {

	/** The skill service. */
	@Autowired
	private SkillService skillService;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Skill convert(String skillIdStr) {
		int skillId = Integer.parseInt(skillIdStr);
		return skillService.getSkillById(skillId);
	}
}
