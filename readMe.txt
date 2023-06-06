Configuration:
    _Mettre toutes les pages jsp dans WEB-INF/vues, si le dossier n' existe pas, creer le
    _Creer une page index.html dans WEB-INF/vues qui sera la page par defaut
    _Creer une page error.html dans WEB-INF/vues qui sera la page appele en cas d' erreur
    _Ajouter WebGeneralizer.jar dans le dossier WEB-INF/lib
    _Configurer web.xml en ajoutant:
        <servlet>
            <servlet-name>frontservlet</servlet-name>
            <servlet-class>etu1832.framework.servlet.FrontServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>frontservlet</servlet-name>
            <url-pattern>/*</url-pattern>
        </servlet-mapping>
        <servlet-mapping>
            <servlet-name>jsp</servlet-name>
            <url-pattern>/WEB-INF/vues/*</url-pattern>
        </servlet-mapping>
Utilisation:
    1) Importer :
        import etu1832.framework.ModelView;
        import etu1832.framework.annotation.RequestMapping;

    2) Annoter les fonctions qui vont traiter une requete avec RequestMapping
        exemple: @RequestMapping(path = "/emp-add")

    3) Chaque fonction annotee doit retourner une instance de ModelView
        _Pour instancier un ModelView utiliser: new ModelView(String nomDeLaPage)
        _Pour envoyer des donnees vers la page, utiliser la fonction de ModelView:
            addItem(<Nom de la cle recuperee avec request.getAttribute(nomCle)>, <Objet a anvoyer>) 

    4) Creer un constructeur vide dans chaque classe

    5) Pour recuperer les donnees d' une page, il faut que:
            _Il faut que le nom des donnees envoyes soit le meme que 
            l' attribut de la classe qui va traiter la requete ou le meme qu' un
            argument de la fonction qui va traiter la requete

    6) Lors de la compilation des classes du projet ajoutez l' option -parameters a javac