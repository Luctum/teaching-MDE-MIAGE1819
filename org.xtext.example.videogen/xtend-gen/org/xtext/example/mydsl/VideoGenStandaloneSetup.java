/**
 * generated by Xtext 2.15.0
 */
package org.xtext.example.mydsl;

import org.xtext.example.mydsl.VideoGenStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class VideoGenStandaloneSetup extends VideoGenStandaloneSetupGenerated {
  public static void doSetup() {
    new VideoGenStandaloneSetup().createInjectorAndDoEMFRegistration();
  }
}
