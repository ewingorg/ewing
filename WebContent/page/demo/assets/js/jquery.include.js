$.extend({
    includePath: '',
    include: function(file)
    {
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++)
        {
            var name = files[i]; 
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css"; 
            if (isCSS){  
              var link = document.createElement('link');
			    link.rel = 'stylesheet';
			    link.type = 'text/css';
			    link.href = name;
			    document.getElementsByTagName('head')[0].appendChild(link);
           }else{ 
                var script = document.createElement('script');
				script.type = 'text/javascript';
				script.src = name; 
				document.getElementsByTagName('head')[0].appendChild(script);
           }
        }
    }
});