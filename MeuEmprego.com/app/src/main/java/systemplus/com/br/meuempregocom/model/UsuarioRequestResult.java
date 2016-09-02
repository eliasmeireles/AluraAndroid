package systemplus.com.br.meuempregocom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elias on 30/08/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioRequestResult {

    @SerializedName(value = "content")
    private List<Usuario> content;

    @SerializedName(value = "links")
    private List<Links> links;

    @SerializedName(value = "page")
    private Page page;


    public List<Usuario> getContent() {
        return content;
    }

    public void setContent(List<Usuario> content) {
        this.content = content;
    }

    public List<Links> getLinks() {
        return links;
    }

    public void setLinks(List<Links> links) {
        this.links = links;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
