# jFormChecker
HTML Forms in Java made easy.  

*Please be aware, that this project will not be maintained by me any longer.* 

I created a successor which is far better:

https://github.com/jochen777/jWebForm

and:
https://github.com/jochen777/jWebForm/blob/master/doc/jformchecker_compare.md


[Quickstart](doc/quickstart.md)

[Documentation](doc/start.md)

[See changelog](doc/CHANGELOG.md)

[![Build Status](https://travis-ci.org/jochen777/jFormchecker.svg?branch=master)](https://travis-ci.org/jochen777/jFormchecker)

## Overview

The form-handling support in current java MVC frameworks is very basic. jFormChecker tries to fill this gap by providing these features:

* central form-definition for reuse and separation of concerns
* handling the request-response loop for forms
* handling error-messages and error-highlighting of the label
* correct tab-order
* pre-fillments of form-elements
* automatically syntactical correct HTML (5) with label and "label-for"
* pre-fillments of user-input after submit
* max-lenght constraint on every element to avoid security issues
* easy validation, easy custom validation
* all standard form fields and more (date-input)
* very compact output in sourcecode
* prebuild Bootstrap Styles
* prebuild ajax handling
* avoiding a lot of boilerplate code
* translateable error-messages.
* minimal dependencies!

jFormChecker is for java what Symfony-Forms is for PHP or Rose::HTML for Perl.

See this project for integration into servlet-based frameworks:

https://github.com/jochen777/jformchecker-integration



## The jFormChecker way

The concept is simple: 

1. Define a form with every element in it

2. Let the central "FormChecker" class handle the validation and processing

3. use a simple tag in the output




## Maven Dependency
```xml
...
<dependency>
    <groupId>de.cyclon-softworx</groupId>
    <artifactId>jformchecker</artifactId>
    <version>0.1.16</version> <!-- check on maven central for the latest version -->
</dependency>
...
```


## Thanks

Thanks to Arman Sharif for his work on jreform.sourceforge.net and the great Critera Classes