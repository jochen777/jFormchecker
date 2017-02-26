# Output of individual HTML

If you need greater control over the HTML and the generic HTML generation is not suitable for you, you can choose the "View".  


## Compose the form in the template

### Complete Form

If you want to build the complete form, just controlled by the FormBuilder Class, use this:

```html
...

{{fc.view.form|raw}}

...
```

### Building the form element by element


```html
...
{{view.start|raw}}

{{view.label("textInput", {"onclick":"alert('hello')"})|raw}}
{{view.input("textInput")|raw}}

{{view.submit|raw}}
{{view.end|raw}}
...
```

