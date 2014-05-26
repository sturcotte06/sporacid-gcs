package gcs.webservices.services;

import gcs.webservices.aspects.Auditable;

import org.springframework.stereotype.Component;

/**
 * @author Simon Turcotte-Langevin
 */
@Auditable
@Component
public class SecureHttpService extends BaseHttpService
{}
