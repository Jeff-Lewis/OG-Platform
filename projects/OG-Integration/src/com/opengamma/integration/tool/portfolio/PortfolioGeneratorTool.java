/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.portfolio;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.financial.generator.AbstractPortfolioGeneratorTool;
import com.opengamma.financial.generator.SecurityGenerator;
import com.opengamma.financial.generator.StaticNameGenerator;
import com.opengamma.id.ExternalId;
import com.opengamma.integration.component.RemoteComponentFactoryToolContextAdapter;
import com.opengamma.integration.tool.AbstractComponentTool;
import com.opengamma.util.functional.Function2;
import com.opengamma.util.money.Currency;

/**
 * Utility for generating a portfolio of securities.
 */
public class PortfolioGeneratorTool extends AbstractPortfolioGeneratorTool {

  protected void configureChain(final SecurityGenerator<?> securityGenerator) {
    super.configureChain(securityGenerator);
    securityGenerator.setCurrencyCurveName("FORWARD");
    securityGenerator.setPreferredScheme(ExternalSchemes.BLOOMBERG_TICKER);
    securityGenerator.setSpotRateIdentifier(new Function2<Currency, Currency, ExternalId>() {
      @Override
      public ExternalId execute(final Currency a, final Currency b) {
        return ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, a.getCode() + b.getCode() + " Curncy");
      }
    });
  }

  public static void main(final String[] args) { // CSIGNORE
    (new AbstractComponentTool() {

      private final PortfolioGeneratorTool _instance = new PortfolioGeneratorTool();

      @Override
      protected Options createOptions() {
        final Options options = super.createOptions();
        _instance.createOptions(options);
        return options;
      }

      @Override
      protected void doRun() throws Exception {
        final CommandLine commandLine = getCommandLine();
        _instance.setCounterPartyGenerator(new StaticNameGenerator(commandLine.getOptionValue(COUNTER_PARTY_OPT, DEFAULT_COUNTER_PARTY)));
        _instance.run(new RemoteComponentFactoryToolContextAdapter(getRemoteComponentFactory()), commandLine);
      }

      @Override
      protected Class<?> getEntryPointClass() {
        return PortfolioGeneratorTool.class;
      }

    }).initAndRun(args);
    System.exit(0);
  }

}
