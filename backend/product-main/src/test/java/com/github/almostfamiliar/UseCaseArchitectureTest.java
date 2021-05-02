package com.github.almostfamiliar;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = TestConfig.PACKAGE_ROOT)
public class UseCaseArchitectureTest {

  static final DescribedPredicate<JavaClass> portInterface =
      JavaClass.Predicates.simpleNameEndingWith("UseCase");

  @ArchTest
  static final ArchRule should_BePackageVisibility_When_IsUsecase =
      classes().that().areAnnotatedWith(Service.class).should().bePackagePrivate();

  @ArchTest
  static final ArchRule should_BeInPackageService_When_IsUsecase =
      classes().that().areAnnotatedWith(Service.class).should().resideInAPackage("..service..");

  @ArchTest
  static final ArchRule should_ImplementOnlyPorts_When_IsUsecase =
      classes().that().areAnnotatedWith(Service.class).should().implement(portInterface);

  @ArchTest
  static final ArchRule should_NotDependOnAdapters_When_IsUsecase =
      noClasses()
          .that()
          .areAnnotatedWith(Service.class)
          .should()
          .dependOnClassesThat()
          .resideInAPackage("..persistence..")
          .orShould()
          .dependOnClassesThat()
          .resideInAPackage("..web..");
}
