package com.havens.seal.proxy;

/**
 * @desc: TODO
 * @author: havens
 * @date: 2016/8/25 11:44
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcreteSubject implements ISubject {

    private static final Logger LOG = LoggerFactory.getLogger(ConcreteSubject.class);

    @Override
    public void action() {
//    LOG.info("ConcreteSubject action()");
    }

}
