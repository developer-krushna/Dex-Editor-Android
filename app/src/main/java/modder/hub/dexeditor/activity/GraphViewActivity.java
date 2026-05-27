/*
 * Dex-Editor-Android an Advanced Dex Editor for Android
 * Copyright 2024-26, developer-krushna
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of developer-krushna nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


 *     Please contact Krushna by email mt.modder.hub@gmail.com if you need
 *     additional information or have any questions
 */

package modder.hub.dexeditor.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.nio.charset.StandardCharsets;
import modder.hub.dexeditor.R;

/*
Author @developer-krushna
Viz.js 3.14.0
Copyright (c) 2023 Michael Daines

This distribution contains other software in object code form:
Graphviz https://www.graphviz.org
Expat https://libexpat.github.io
*/

public class GraphViewActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra("Title"));
            getSupportActionBar().setSubtitle(getIntent().getStringExtra("Subtitle"));
        }

        String dotContent = getIntent().getStringExtra("dot");
        if (dotContent == null) dotContent = "";
        
        // Useing Base64 to safely pass the DOT string to JavaScript
        String base64Dot = Base64.encodeToString(dotContent.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);

        WebView webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);

        // HTML template to render DOT using viz-js with better error handling and compatibility
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "        body { margin: 0; padding: 10px; display: flex; justify-content: center; background: #f5f5f5; }\n" +
                "        #graph { width: 100%; text-align: center; }\n" +
                "        svg { width: 100%; height: auto; }\n" +
                "        .error { color: red; font-family: monospace; padding: 20px; text-align: left; background: #ffebee; border: 1px solid #ffcdd2; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"graph\">Rendering Graph...</div>\n" +
                "    <script src=\"file:///android_asset/viz-standalone.js\"></script>\n" +
                "    <script>\n" +
                "        (function() {\n" +
                "            var base64Dot = '" + base64Dot + "';\n" +
                "            var dot = decodeURIComponent(escape(window.atob(base64Dot)));\n" +
                "            \n" +
                "            function render() {\n" +
                "                if (typeof Viz === 'undefined') {\n" +
                "                    document.getElementById('graph').innerHTML = '<div class=\"error\">Error: Viz library not found in assets.</div>';\n" +
                "                    return;\n" +
                "                }\n" +
                "                \n" +
                "                Viz.instance().then(function(viz) {\n" +
                "                    var svg = viz.renderSVGElement(dot);\n" +
                "                    var graphDiv = document.getElementById('graph');\n" +
                "                    graphDiv.innerHTML = '';\n" +
                "                    graphDiv.appendChild(svg);\n" +
                "                }).catch(function(err) {\n" +
                "                    document.getElementById('graph').innerHTML = '<div class=\"error\">Viz Error: ' + err.message + '</div>';\n" +
                "                });\n" +
                "            }\n" +
                "\n" +
                "            // Try to render when script is loaded\n" +
                "            if (window.Viz) {\n" +
                "                render();\n" +
                "            } else {\n" +
                "                window.onload = render;\n" +
                "                // Fallback timeout\n" +
                "                setTimeout(function() {\n" +
                "                    if (document.getElementById('graph').innerHTML.indexOf('Loading') !== -1) {\n" +
                "                        render();\n" +
                "                    }\n" +
                "                }, 5000);\n" +
                "            }\n" +
                "        })();\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";

        webView.loadDataWithBaseURL("https://cdn.jsdelivr.net", html, "text/html", "UTF-8", null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

