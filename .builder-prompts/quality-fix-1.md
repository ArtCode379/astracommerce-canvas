Fix the Android project at /tmp/astracommerce-canvas so the failing quality-fix-1 step passes.

Use these orchestrator instructions: /home/codex-agent/codex-app-agent/AGENTS.md
Screen spec: /home/codex-agent/codex-app-agent/screens-shop.md
Do not push to GitHub, do not update Asana, and do not send Slack.
Fix formatting failures by expanding the affected Kotlin code; do not suppress or bypass the formatting checks.

Recent failure log:
```text
=== QUALITY CHECK: /tmp/astracommerce-canvas ===

WARN: Only 1 commit(s) — final implementation commit may not exist yet
FAIL: app/src/main/java/astracommercetrade/art/astracanvas/data/repository/ProductRepository.kt has 1 entries (need >=10)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_3.jpg (colors=13817, entropy=0.838473)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_6.jpg (colors=7137, entropy=0.874675)
  OK: 9 images
  OK: All images valid
FAIL: 2 placeholder-like drawable image(s); use real photos or filesystem-backed imagegen output, not local generated placeholders
  OK: No empty onClick
  OK: No obvious no-op onClick handlers
  OK: icon.png (198637B, 512x512, rounded opaque canvas, transparent corners)
FAIL: Manifest references .SkeletonApplication but class not found — CRASH
  OK: HomeScreen.kt: 260 lines
  OK: No project-local agent instruction files
  OK: dynamicColor not enabled
  OK: Google Fonts dependency found
FAIL: font_certs.xml missing
  OK: HorizontalPager used
  OK: No drawable resources detected in AsyncImage lines
  OK: Kotlin source formatting

=== RESULT: 4 error(s) ===
FIX ALL ISSUES BEFORE PUSH

```
