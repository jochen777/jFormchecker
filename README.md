# jFormChecker
The pragmatic, compact, fast library to build HTML Forms in MVC-Framworks. 

The form-handling support in current java MVC frameworks is very basic. jFormChecker tries to fill this gap by providing these features:

* central form-definition for reuse and separation of concerns
* handling the request-response loop for forms
* handling error-messages and error-highlighting of the label
* correct tab-order
* pre-fillments of form-elements
* automatically syntactical correct HTML (5) with label and "label-for"
* pre-fillments of user-input after submit
* easy validation, easy custom validation
* all standard form fields and more (date-input)
* Very compact output in sourcecode
* Avoiding a lot of boilerplate code

jFormChecker is for java what Symfony-Forms is for PHP or Rose::HTML for Perl.



## The jFormChecker way

The concept is simple: 

1. Define a form with every element in it

2. Let the central "FormChecker" class handle the validation and processing

3. use a simple tag in the output

Example Form-Definiton:

```Java

public class ExampleForm extends FormCheckerForm {

  public ExampleForm() {
     add(TextInput.build("firstname").
     	setDescription("Your Firstname").
     	setPreSetValue("Jochen").
     	setCriterias(Criteria.accept("Jochen", "John")));

    add(TextInput.build("lastname").
    	setDescription("Your lastname:").
    	setPreSetValue("Pier").
    	setCriterias(Criteria.accept("Pier", "Doe")));
	}
}
```

Controller code:

```java

 	FormChecker fc = FormChecker.
        build("id", request, new ExampleForm()).
        run();
             
    model.add("fc", fc);

```


Example template output:

```html
...
<h1>The form</h1>

${fc.genericForm}

<p>Lorem ipsum</p>
...
```

(yes it is that simple!)

Alternativly you can output the elements manually if you need more control over your html:

```html
...
<h1>The form</h1>
<div class="lastnameedit">
${fc.elements.lasntame.label}
${fc.elements.lasntame.inputTag}
</div>
...
```

## Remember: Standard form handling without jFormChecker

Without jFormChecker, html forms must be constructed manually in the template.

Example-Definition of a form in spring-mvc:
```java
public class Adress {

    @Size(min=2, max=30)
    private String name;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

	...
}

```

Example-Definition of a form in html (spring-forms)
```html

        <form:form class="form-horizontal" method="post" 
                modelAttribute="userForm" action="${userActionUrl}">
                <form:hidden path="id" />

		<spring:bind path="name">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label" label-for="name">Name</label>
			<div class="col-sm-10">
				<form:input path="name" type="text" class="form-control" 
                                id="name" placeholder="Name" />
				<form:errors path="name" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="email">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label" label-for="email">Email</label>
			<div class="col-sm-10">
				<form:input path="email" class="form-control" 
                                id="email" placeholder="Email" />
				<form:errors path="email" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="country">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label" label-for="country">Country</label>
			<div class="col-sm-5">
				<form:select path="country" class="form-control">
					<form:option value="NONE" label="--- Select ---" />
					<form:options items="${countryList}" />
				</form:select>
				<form:errors path="country" class="control-label" />
			</div>
			<div class="col-sm-5"></div>
		  </div>
		</spring:bind>
		
		...
		
			<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			     <button type="submit" class="btn-lg btn-primary pull-right">Add
                             </button>
		  </div>
		</div>
	</form:form>

```

As you can see, especially on the template side, you have to enter a lot of stuff for basic highlighting errors...

## Thanks

Thanks to Arman Sharif for his work on jreform.sourceforge.net and the great Critera Classes