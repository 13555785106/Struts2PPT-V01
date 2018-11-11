package org.crazyit.app.service.impl;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import org.crazyit.app.service.*;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Component
public class Chinese implements Person
{
	@Autowired
	@Qualifier("steelAxe")
	private Axe axe;
//	// axe��setter����
//	@Autowired
//	public void setAxe(@Qualifier("stoneAxe") Axe axe)
//	{
//		this.axe = axe;
//	}
	// ʵ��Person�ӿڵ�useAxe()����
	public void useAxe()
	{
		// ����axe��chop()������
		// ����Person����������axe����
		System.out.println(axe.chop());
	}
}