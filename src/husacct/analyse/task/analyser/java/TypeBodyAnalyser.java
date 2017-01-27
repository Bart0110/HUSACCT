package husacct.analyse.task.analyser.java;

import java.util.List;

import husacct.analyse.infrastructure.antlr.java.Java7Parser.AnnotationContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.AnnotationTypeBodyContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.AnnotationTypeElementDeclarationContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.ClassBodyContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.ClassBodyDeclarationContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.EnumBodyDeclarationsContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.EnumConstantContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.EnumConstantsContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.ExpressionContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.InterfaceBodyContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.InterfaceBodyDeclarationContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.InterfaceMemberDeclarationContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.MemberDeclarationContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.ModifierContext;
import husacct.analyse.infrastructure.antlr.java.Java7Parser.TypeTypeContext;

class TypeBodyAnalyser extends JavaGenerator {

    private String belongsToClass;
 
    public TypeBodyAnalyser(String belongsToClass) {
    	this.belongsToClass = belongsToClass;
        /* Test helpers
    	if (belongsToClass.equals("domain.direct.violating.DeclarationReturnType")) {
    		//if (TypeBodyAnalyser.start.getLine() == 6) {
    				boolean breakpoint = true;
    		//}
    	} */
    }
 
	public void analyseClassBody(ClassBodyContext classBody) {
		if (classBody.classBodyDeclaration() != null) {
			for (ClassBodyDeclarationContext classBodyDeclaration : classBody.classBodyDeclaration()) {
				analyseClassBodyDeclaration(classBodyDeclaration);
			}
		}
	}
	
	public void analyseClassBodyDeclaration(ClassBodyDeclarationContext classBodyDeclaration) {
    	if (classBodyDeclaration.block() != null) {
    		new BlockAnalyser(classBodyDeclaration.block(), belongsToClass, "");
    	} else if (classBodyDeclaration.memberDeclaration() != null) {
    		List<ModifierContext> modifierList = classBodyDeclaration.modifier();
    		MemberDeclarationContext member = classBodyDeclaration.memberDeclaration();
			if (member.methodDeclaration() != null) {
				MethodAnalyser methodAnalyser = new MethodAnalyser(belongsToClass);
				methodAnalyser.analyseMethod(modifierList, member.methodDeclaration());
			} else if (member.genericMethodDeclaration() != null) {
				MethodAnalyser methodAnalyser = new MethodAnalyser(belongsToClass);
				methodAnalyser.analysegenericMethod(modifierList, member.genericMethodDeclaration());
			} else if (member.fieldDeclaration() != null) {
				VariableAnalyser variableAnalyser = new VariableAnalyser(belongsToClass);
				variableAnalyser.analyseVariable(modifierList, member.fieldDeclaration());
			} else if (member.constructorDeclaration() != null) {
				MethodAnalyser methodAnalyser = new MethodAnalyser(belongsToClass);
				methodAnalyser.analyseConstructor(modifierList, member.constructorDeclaration());
			} else if (member.genericConstructorDeclaration() != null) {
				MethodAnalyser methodAnalyser = new MethodAnalyser(belongsToClass);
				methodAnalyser.analyseGenericConstructor(modifierList, member.genericConstructorDeclaration());
			} else if (member.interfaceDeclaration() != null) {
				TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
				nestedTypeAnalyser.analyseNestedInterfaceDeclaration(modifierList, member.interfaceDeclaration(), belongsToClass);
			} else if (member.annotationTypeDeclaration() != null) {
				TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
				nestedTypeAnalyser.analyseNestedAnnotationTypeDeclaration(modifierList, member.annotationTypeDeclaration(), belongsToClass);
			} else if (member.classDeclaration() != null) {
				TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
				nestedTypeAnalyser.analyseNestedClassDeclaration(modifierList, member.classDeclaration(), belongsToClass);
			} else if (member.enumDeclaration() != null) {
				TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
				nestedTypeAnalyser.analyseNestedEnumDeclaration(modifierList, member.enumDeclaration(), belongsToClass);
			}
    	}
	}
	
	public void analyseInterfaceBody(InterfaceBodyContext interfaceBody) {
		for (InterfaceBodyDeclarationContext interfaceBodyDeclaration : interfaceBody.interfaceBodyDeclaration()) {
			if (interfaceBodyDeclaration.interfaceMemberDeclaration() != null) {
        		List<ModifierContext> modifierList = interfaceBodyDeclaration.modifier();
				InterfaceMemberDeclarationContext member = interfaceBodyDeclaration.interfaceMemberDeclaration();
				if (member.constDeclaration() != null) {
					VariableAnalyser variableAnalyser = new VariableAnalyser(belongsToClass);
					variableAnalyser.analyseConstant(modifierList, member.constDeclaration());
				} else if (member.interfaceMethodDeclaration() != null) {
					MethodAnalyser methodAnalyser = new MethodAnalyser(belongsToClass);
					methodAnalyser.analyseInterfaceMethod(modifierList, member.interfaceMethodDeclaration());
				} else if (member.genericInterfaceMethodDeclaration() != null) {
					MethodAnalyser methodAnalyser = new MethodAnalyser(belongsToClass);
					methodAnalyser.analyseGenericInterfaceMethod(modifierList, member.genericInterfaceMethodDeclaration());
				} else if (member.interfaceDeclaration() != null) {
					TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
					nestedTypeAnalyser.analyseNestedInterfaceDeclaration(modifierList, member.interfaceDeclaration(), belongsToClass);
				} else if (member.annotationTypeDeclaration() != null) {
					TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
					nestedTypeAnalyser.analyseNestedAnnotationTypeDeclaration(modifierList, member.annotationTypeDeclaration(), belongsToClass);
				} else if (member.classDeclaration() != null) {
					TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
					nestedTypeAnalyser.analyseNestedClassDeclaration(modifierList, member.classDeclaration(), belongsToClass);
				} else if (member.enumDeclaration() != null) {
					TypeDeclarationAnalyser nestedTypeAnalyser = new TypeDeclarationAnalyser();
					nestedTypeAnalyser.analyseNestedEnumDeclaration(modifierList, member.enumDeclaration(), belongsToClass);
				}
			}
		}
	}
	
	public void analyseEnumBody(EnumConstantsContext enumConstants, EnumBodyDeclarationsContext enumBodyDeclarations) {
		if (enumConstants != null) {
			for (EnumConstantContext enumConstant : enumConstants.enumConstant()) {
				if ((enumConstant.annotation() != null) && !enumConstant.annotation().isEmpty()) {
					for (AnnotationContext annotation : enumConstant.annotation()) {
						new AnnotationAnalyser(annotation, belongsToClass);
					}
				}
				if (enumConstant.Identifier() != null) {
					VariableAnalyser variableAnalyser = new VariableAnalyser(belongsToClass);
					variableAnalyser.analyseEnumConstant(enumConstant.Identifier());
				}
				if ((enumConstant.arguments() != null) && (enumConstant.arguments().expressionList() != null) && 
						(enumConstant.arguments().expressionList().expression() != null)) {
					for (ExpressionContext expression : enumConstant.arguments().expressionList().expression()) {
						new ExpressionAnalyser(belongsToClass, "", expression);
					}
				}
				if (enumConstant.classBody() != null) {
					analyseClassBody(enumConstant.classBody());
				}
			}
		}
		if ((enumBodyDeclarations != null) && (enumBodyDeclarations.classBodyDeclaration() != null)) {
			for (ClassBodyDeclarationContext classBodyDeclaration : enumBodyDeclarations.classBodyDeclaration()) {
				analyseClassBodyDeclaration(classBodyDeclaration);
			}
		}
	}

	public void analyseAnnotationBody(AnnotationTypeBodyContext annotationTypeBody) {
		if ((annotationTypeBody != null) && (annotationTypeBody.annotationTypeElementDeclaration() != null)) {
			for (AnnotationTypeElementDeclarationContext element : annotationTypeBody.annotationTypeElementDeclaration()) {
	       		List<ModifierContext> modifierList = element.modifier();
				if (element.annotationTypeElementRest() != null) {
					if (element.annotationTypeElementRest().typeType() != null) {
						TypeTypeContext typeType = element.annotationTypeElementRest().typeType();
						if (element.annotationTypeElementRest().annotationMethodOrConstantRest() != null) {
							if (element.annotationTypeElementRest().annotationMethodOrConstantRest().annotationConstantRest() != null) {
								VariableAnalyser variableAnalyser = new VariableAnalyser(belongsToClass);
								variableAnalyser.analyseAnnotationConstant(modifierList, typeType, 
								element.annotationTypeElementRest().annotationMethodOrConstantRest().annotationConstantRest().variableDeclarators());
							} else if (element.annotationTypeElementRest().annotationMethodOrConstantRest().annotationMethodRest() != null) {
								MethodAnalyser methodAnalyser = new MethodAnalyser(belongsToClass);
								methodAnalyser.analyseAnnotationMethod(modifierList, typeType, 
										element.annotationTypeElementRest().annotationMethodOrConstantRest().annotationMethodRest().Identifier());
							}
						}
					}
				}
			}
		}
	}
}
