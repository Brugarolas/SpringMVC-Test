package springPrueba;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public class Methods {
	public static void assertResponseContains(MockMvc mockMvc, String url, String ... text) throws Exception {
		MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andReturn();
    	
        String content = result.getResponse().getContentAsString();
        assertNotNull(content);
        
        for (String string : text) {
        	assertTrue(content.contains(string));
        }
	}
}
