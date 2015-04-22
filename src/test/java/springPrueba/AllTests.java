package springPrueba;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import springPrueba.vista.ElementoControllerTest;
import springPrueba.vista.ListaControllerTest;
import springPrueba.vista.MainPagesAreUpTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
  MainPagesAreUpTest.class, 
  ListaControllerTest.class,
  ElementoControllerTest.class}
)

public class AllTests {}